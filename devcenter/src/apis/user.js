import { easyGet } from './base.js'

export function GetInfo () {
  return easyGet('/api/user/current')
}
