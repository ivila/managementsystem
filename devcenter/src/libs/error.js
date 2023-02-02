export class ValidateError {
  constructor (detail) {
    this.detail = detail
  }
}

export class APIError {
  constructor (detail) {
    this.retcode = detail['retCode']
    this.data = detail['data']
    this.message = detail['message']
  }
}
