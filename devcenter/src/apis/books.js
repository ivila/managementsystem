import { easyGet, easyPost } from './base.js'

export function Get(params, headers) {
  return easyGet('/api/book/get', params, headers, true)
}

export function List(params, headers) {
  return easyPost('/api/book/list', params, headers, true)
}

export function Create(params, headers) {
  return easyPost('/api/book/create', params, headers, true)
}

export function Update(params, headers) {
  return easyPost('/api/book/update', params, headers, true)
}

export function DoDelete(params, headers) {
  return easyPost('/api/book/delete', params, headers, true)
}
