import axios from 'axios'
import { ElMessage } from 'element-plus'
import { ErrCode } from './libs/constant'
import { APIError, ValidateError } from './libs/error'
import { reCheckPermission, router } from './router/index.js'


export const handleError = (err, vm, info) => {
  if (err instanceof ValidateError) {
    return
  }
  // 到这里开始都是要处理的
  if (err instanceof APIError) {
    if (err.retcode === ErrCode.NEED_LOGIN) {
      reCheckPermission()
      ElMessage.error(`Please Login first`)
      router.push('/login')
      return
    }
    if (err.retcode === ErrCode.INVALID_REQUEST) {
      ElMessage.error(`An uncaught API INVALID_REQUEST_ERROR occured, please keep your log and contact our dev for help`)
      console.error('uncaught exception: INVALID_REQUEST', err, vm, info)
      return
    }
    if (err.retcode === ErrCode.WRONG_REQUEST) {
      ElMessage.warning(`An uncaught API WRONG_REQUEST have occured, please retry again or contact our dev for help`)
      console.error('uncaught exception: WRONG_REQUEST', err, vm, info)
      return
    }
    ElMessage.error(`An uncaught UNKNOW_API_ERROR occured, please keep your log and contact our dev for help`)
    console.error('uncaught exception: UNKNOW_API_ERROR', err, vm, info)
    return
  }
  if (axios.isAxiosError(err)) {
    console.error('axios error', err, vm, info)
    if (err.response.status === 401) {
      ElMessage.error({
        message: 'Token expired, please login again',
        duration: 10 * 1000,
        showClose: true
      })
      window.location.reload()
      return
    }
    ElMessage.error({
      message: `A HTTP Request to '${err.config.url}' has failed due to '${err.toString()}'`,
      duration: 10 * 1000,
      showClose: true
    })
    return
  }
  console.error('unexpected error', err, vm, info)
  ElMessage.error({
    message: 'A unexpected error, please keep your brower log and contact our dev for help',
    duration: 10 * 1000,
    showClose: true
  })
}
