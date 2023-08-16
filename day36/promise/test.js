let prom = new Promise(
	(resolve, reject) => {
		setTimeout(() => {
			//reject('error!')
			resolve('all good')
			console.info('in promise')
		}, 1000)
	}
).then(
	value => {
		console.info('result from promise: ', value)
		throw "I have an error"
	}
).then(
	value => {
		console.info('result from after then: ', value)
	}
).catch(
	error => {
		console.error('error: ', error)
		return "problem resolved";
	}
).then(
	value => {
		console.info('after catch: ', value)
	}
)

console.info('>>> promise: ', prom)
console.info('hello, world')
