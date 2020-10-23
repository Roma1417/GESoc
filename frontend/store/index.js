
export const state = () => {
  return {
    userInfo: {}
  }
}
export const mutations = {
  setUserInfo (state, userInfo) {
    state.userInfo = userInfo
  }
}
