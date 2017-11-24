const express = require('express')
const router = express.Router()
const db = require('./database.js')

router.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*')
  res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept')
  next()
})

router.get('/', (req, res, next) => {
  res.send('API endpoint')
})

router.get('/randomthemes/:count', (req, res, next) => {
  db.getRandomCategories(req.params.count).then((val) => {
    res.send(JSON.stringify(val))
  })
})

router.get('/randomquestions/:theme/:count', (req, res, next) => {
  db.getRandomQuestions(req.params.theme, req.params.count).then((val) => {
    res.send(JSON.stringify(val))
  })
})

router.get('/scores/:count', (req, res, next) => {
  db.getBestScores(req.params.count).then((val) => {
    res.send(JSON.stringify(val))
  })
})

router.post('/scores/:name/:theme/:score', (req, res, next) => {
  db.addScore(req.params.name, req.params.theme, req.params.score).then((val) => {
    res.send(JSON.stringify(val))
  })
})

module.exports = router
