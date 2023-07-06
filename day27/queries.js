
db = connect('mongodb://localhost:27017/bgg')

printjson(

	db.comments.find(
		{
			$text: {
				$search: "great game"
			}
		},
		{
			matchScore: {
				$meta: "textScore"
			}
		}
	)
	.limit(4)
	.skip(4)
	.sort({ matchScore: -1 })
	//.projection({ c_text: 1, matchScore: 1, _id: 0 })

)

/*
printjson(
	db.tvshow.findOne()
)
*/

