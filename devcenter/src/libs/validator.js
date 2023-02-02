
export const MustJSON = (rule, value, callback) => {
  try {
    JSON.parse(value)
  } catch (err) {
    callback(rule.message ? rule.message : 'must json')
    return
  }
  callback()
}
