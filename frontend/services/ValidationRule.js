export default class ValidationRule {
  constructor (ctx) {
    this.i18n = ctx.i18n
  }

  /**
   * Validate that it is not empty
   * @param inputName (string)
   * @returns string
   */
  required (inputName) {
    return v => !!v || this.i18n.t('validations.required',
      {
        attribute: this.attributeTranslate(inputName)
      }
    )
  }

  /**
   * Validate maximum length
   * @param inputName (string)
   * @param length (int)
   * @param type (string) | Ej: numeric, file, string, array
   * @returns string
   */
  maxLength (inputName, length, type) {
    return v => (v && v.length <= length) || this.i18n.t('validations.max.' + type,
      {
        attribute: this.attributeTranslate(inputName),
        max: length
      })
  }

  /**
   * Validate positive amount
   * @param inputName (string)
   * @returns string
   */
  positive (inputName) {
    return v => (!!v && v > 0) || this.i18n.t('validations.positive',
      {
        attribute: this.attributeTranslate(inputName)
      })
  }

  /**
   * Translate attribute name
   * @param inputName (string)
   * @returns string
   */
  attributeTranslate (inputName) {
    return this.i18n.t('attributes.' + inputName)
  }
}
