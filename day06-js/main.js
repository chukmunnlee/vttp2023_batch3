// function greetings(name) {
//     console.info(`Hello ${name}`)
// }

const greetings = function(name) {
    console.info(`Hello ${name}`)
}

const name = 'wilma'

greetings('fred')
greetings('barney')
greetings(name)

console.info(typeof(name))
console.info(typeof(greetings))
console.info('value of name: ', name)
console.info('value of greetings: ', greetings)