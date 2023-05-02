function addTwoNumbers(x, y) {
    return x + y
}

// anonymous functions
let add = (x, y) => {
    return x + y
}

let mul = (x, y) => {
    return x * y
}

let sub = (x, y) => {
    return x - y
}

let apply = (f, x, y) => {
    return f(x, y)
}

const value0 = 3
const value1 = 4
const answer = add(value0,value1)
const addAns = apply(add, value0, value1)
const mulAns = apply(mul, value0, value1)
const subAns = apply(sub, value0, value1)

console.info(`${value0} + ${value1} = ${answer}`)
console.info(`${addAns}, ${mulAns}, ${subAns}`)

const operators = [ add, sub, mul ]
const vals0 = [ 3, 4, 5 ]
const vals1 = [ 7, 8, 9 ]

for (let i = 0; i < operators.length; i++) {
    const a = apply(operators[i], vals0[i], vals1[i])
    console.info(`answer = ${a}`)
}

// (4 * 5) + (8 - 2)
const a = apply(add, apply(mul, 4, 5), apply(sub, 8, 2))