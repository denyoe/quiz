import { combineReducers } from 'redux'
import * as actionType from '../actions/types'

const initState = null;
const token = (state = initState, action) => {
    switch (action.type) {
        case actionType.SET_TOKEN:
            return action.data
        default:
            return state
    }
}

const appReducer = combineReducers({
    token
})

const rootReducer = (state, action) => {
    return appReducer(state, action)
}

export default rootReducer