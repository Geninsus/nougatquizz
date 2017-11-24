const express = require('express')
const router = require('./router.js')
const app = express()

const db = require('./database.js')
db.init()

app.use('/api', router)

app.get('/', (req, res) => {
  res.send('Server is running')
})

app.listen(8080, () => {
  console.log('API on port 8080')
})
