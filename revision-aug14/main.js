
function hello(name = 'handsome') {
	console.info('hello ', name)
}

hello('fred')
hello()

/*
const arr = [ 1, 2, 3, 4, 5 ]
console.info('array: ', arr)

const even = arr.filter((v, i, a) => {
		console.info(`v = ${v}, i = ${i}`)
		console.info(`\t a = `, a)
		return 0 == (v % 2)
	})

console.info('even array: ', even)

*/
