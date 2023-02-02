// 我们这里用的是stage0的decorator，虽然现在已经是stage2了，但是前端的世界，能跑就行
import { ElMessage } from 'element-plus'
import { ValidateError } from '@/libs/error'

export const checkFormValidate = (ref='form') => {
  return (target, key, descriptor) => {
    const origin = descriptor.value
    descriptor.value = async function () {
      let form = this.$refs[ref]
      try {
        await form.validate()
      } catch (err) {
        throw new ValidateError(err)
      }
      let resp = await origin.apply(this, arguments)
      return resp
    }
    return descriptor
  }
}

export const toggleParam = (param='loading') => {
  return (target, key, descriptor) => {
    const origin = descriptor.value
    descriptor.value = async function () {
      const dataObj = this
      if (dataObj[param]) {
        ElMessage.warning(`Please Don't Repeat Your Action To Quick`)
        throw new ValidateError('repeat action')
      }
      try {
        dataObj[param] = !dataObj[param]
        let resp = await origin.apply(this, arguments)
        return resp
      } finally {
        dataObj[param] = !dataObj[param]
      }
    }
    return descriptor
  }
}
