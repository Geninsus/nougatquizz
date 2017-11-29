const jsonfile = require('jsonfile')
var questions = {}
var scores = {}

function compareNumbers (a, b) {
  return Number(a) - Number(b)
}

function shuffleArray (array) {
  let myArray = array
  for (var i = array.length - 1; i > 0; i--) {
    let j = Math.floor(Math.random() * (i + 1))
    let temp = myArray[i]
    myArray[i] = myArray[j]
    myArray[j] = temp
  }
  return myArray
}

exports.init = () => {
  jsonfile.readFile('./questions.json', (err, data) => {
    if (!err) {
      questions = data
    } else {
      console.log(err)
    }
  })

  jsonfile.readFile('./scores.json', (err, data) => {
    if (!err) {
      scores = data
    } else {
      console.log(err)
    }
  })
}

exports.getRandomCategories = async (count) => {
  let categories = Object.keys(questions)
  let tmp = []
  let i = 0

  while (i < count) {
    let newElt = categories[Math.floor(Math.random() * categories.length)]
    if (tmp.indexOf(newElt) === -1) {
      tmp.push(newElt)
      i++
    }
  }

  return tmp
}

exports.getRandomQuestions = async (categorie, count) => {
  let avaliable = questions[categorie]
  let tmp = []
  let i = 0

  if (count > 0) {
    while (i < count) {
      let newElt = avaliable[Math.floor(Math.random() * avaliable.length)]
      if (tmp.indexOf(newElt) === -1) {
        tmp.push(newElt)
        i++
      }
    }
    return tmp
  } else {
    return shuffleArray(avaliable)
  }
}

exports.getBestScores = async (count) => {
  /* let avaliable = Object.keys(scores)
  let tmp = []
  let i = 0

  console.log(avaliable.sort(compareNumbers)) */
  return 0
}

exports.addScore = async (name, categorie, score) => {
  return 0
}
