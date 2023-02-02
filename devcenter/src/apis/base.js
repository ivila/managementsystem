import axios from 'axios'
import { ElMessage } from 'element-plus'
import { APIError } from '@/libs/error.js'

// interceptors 不是万能的，甚至有可能带来脏逻辑，所以不建议使用
const config = {
  timeout: 10000,
}

const apiService = axios.create(config)

export const get = (url, params, headers) => {
  return apiService({
    url: url,
    method: 'get',
    params: params,
    headers: headers
  })
}

export const post = (url, data, headers) => {
  return apiService({
    url: url,
    method: 'post',
    data: data,
    headers: headers
  })
}

export const easyGet = async (url, params, extraHeaders) => {
  const headers = Object.assign({}, extraHeaders)
  let response = await get(url, params, headers)
  return commonHandle(response)
}

export const easyPost = async (url, data, extraHeaders, showNotify) => {
  const headers = Object.assign({}, extraHeaders)
  let response = await post(url, data, headers)
  return commonHandle(response, showNotify)
}

export const commonHandle = (response, showNotify) => {
  const res = response.data
  if (res['retCode'] === 0) {
    return res['body']
  }
  if (showNotify) {
    ElMessage.error(res['message'])
  }
  throw new APIError(res)
}
