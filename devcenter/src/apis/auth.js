import { easyPost } from './base.js'

export function Login(params, headers) {
  return easyPost('/api/auth/login', params, headers)
}

export function Logout(params, headers) {
  return easyPost('/api/auth/logout', params, headers)
}
