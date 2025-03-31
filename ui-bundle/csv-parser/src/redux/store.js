import { configureStore } from '@reduxjs/toolkit'
import logsReducer from './reducer/logs';

export const makeStore = () => {
  return configureStore({
    reducer: {
      logs: logsReducer
    }
  })
}