module.exports = {
  root: true,
  env: {
    browser: true,
    node: true
  },
  parserOptions: {
    parser: 'babel-eslint'
  },
  extends: [
    '@nuxtjs',
    'plugin:nuxt/recommended'
  ],
  plugins: [
  ],
  // add your custom rules here
  rules: {
    "complexity": [1, 7],
    "max-statements": [1, 25],
    "max-nested-callbacks": [1, 3],
    "max-depth": [1, {
      "max": 4
    }],
    "max-lines": [1, 350]
  }
}
