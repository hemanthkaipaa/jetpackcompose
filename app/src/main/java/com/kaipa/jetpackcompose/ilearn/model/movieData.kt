package com.kaipa.jetpackcompose.ilearn.model

data class MovieData(
    val id : String="",
    val title : String="",
    val year : String="",
    val genre : String="",
    val director : String="",
    val actors : List<String> = listOf(),
    val plot : String = "",
    val poster : String = "",
    val imdbRating : String="",
    val images:List<String> = listOf()
)

fun getMovies(): List<MovieData> {
    return listOf(
        MovieData(
            id = "1",
            title = "Game Changer",
            year = "2025",
            genre = "Political, Action, Drama",
            director = "S. Shankar",
            actors = listOf("Ram Charan", "Kiara Advani", "S.J. Suryah", "Srikanth"),
            plot = "An honest IAS officer battles a corrupt political system to bring about fair elections and governance.",
            poster = "https://i.pinimg.com/736x/03/12/ab/0312ab0f4ce788485ae4c3e9e060da41.jpg",
            imdbRating = "8.1",
            images = listOf(
                "https://i.pinimg.com/736x/d6/f7/34/d6f734f806f42d2e5e0f88ff2898087b.jpg",
                "https://i.pinimg.com/1200x/54/bb/67/54bb67da06f8471649d7a4738cbcb31e.jpg",
                "https://i.pinimg.com/736x/d7/42/dd/d742dd945d88dfe1ea2699ec7520ca63.jpg"
            )
        ),
        MovieData(
            id = "2",
            title = "Pushpa 2: The Rule",
            year = "2024",
            genre = "Action, Crime, Drama",
            director = "Sukumar",
            actors = listOf("Allu Arjun", "Fahadh Faasil", "Rashmika Mandanna", "Jagapathi Babu"),
            plot = "Pushpa Raj's rule over the red sandalwood smuggling syndicate is challenged by the vengeful SP Bhanwar Singh Shekhawat.",
            poster = "https://i.pinimg.com/736x/f5/ec/3b/f5ec3b1c4430e2bd3ebe82d6e4636464.jpg",
            imdbRating = "8.4",
            images = listOf(
                "https://i.pinimg.com/736x/1e/35/f8/1e35f8e7395ebc3631a66221a5fb2b77.jpg",
                "https://i.pinimg.com/1200x/07/a0/32/07a032dc28bce7983553505e07b82d7c.jpg",
                "https://i.pinimg.com/736x/23/10/c5/2310c5a0b2ede640a320f08eb34cf3ff.jpg"
            )
        ),
        MovieData(
            id = "3",
            title = "Kalki 2898 AD",
            year = "2024",
            genre = "Sci-Fi, Action, Mythological",
            director = "Nag Ashwin",
            actors = listOf("Prabhas", "Amitabh Bachchan", "Deepika Padukone", "Kamal Haasan"),
            plot = "In a post-apocalyptic world in the year 2898 AD, a modern avatar of Vishnu is believed to have descended to protect the earth from evil forces.",
            poster = "https://i.pinimg.com/1200x/fc/d5/8e/fcd58ef69de6fe0bceeeb74e07c5e36d.jpg",
            imdbRating = "7.6",
            images = listOf(
                "https://i.pinimg.com/736x/e8/a5/50/e8a5503004097337464781c1a6c555a4.jpg",
                "https://i.pinimg.com/736x/1b/e5/9f/1be59f1a26f45ba18bfa1dbc277e4f22.jpg",
                "https://i.pinimg.com/736x/fd/2c/41/fd2c41402a2672b536647bb3d2ca8d46.jpg"
            )
        ),
        MovieData(
            id = "4",
            title = "Devara: Part 1",
            year = "2024",
            genre = "Action, Drama",
            director = "Koratala Siva",
            actors = listOf("N.T. Rama Rao Jr.", "Janhvi Kapoor", "Saif Ali Khan", "Prakash Raj"),
            plot = "A fearless man from a coastal region embarks on a mission to safeguard his people, instilling fear in the hearts of evildoers.",
            poster = "https://i.pinimg.com/1200x/0c/97/84/0c9784e7c3c4d08a7305fdca4e000b8f.jpg",
            imdbRating = "6.5",
            images = listOf(
                "https://i.pinimg.com/1200x/1e/26/dc/1e26dc9248c7cc08bb9ea81af72de773.jpg",
                "https://i.pinimg.com/1200x/88/01/f1/8801f16c7f613dcb3f3bec474460d19b.jpg",
                "https://i.pinimg.com/736x/f7/4e/10/f74e10ce35a37e345cbf624db927efbb.jpg"
            )
        ),
        MovieData(
            id = "5",
            title = "Hanu-Man",
            year = "2024",
            genre = "Superhero, Fantasy, Action",
            director = "Prasanth Varma",
            actors = listOf("Teja Sajja", "Amritha Aiyer", "Varalaxmi Sarathkumar", "Vinay Rai"),
            plot = "An ordinary man in the village of Anjanadri gains the powers of Lord Hanuman and must save his people from a power-hungry villain.",
            poster = "https://i.pinimg.com/1200x/17/65/c7/1765c751399374b8cab2603debe62685.jpg",
            imdbRating = "7.9",
            images = listOf(
                "https://i.pinimg.com/736x/f3/28/00/f32800e0620cf2e95ac3e2fae6aab967.jpg",
                "https://i.pinimg.com/736x/13/bf/b2/13bfb2f48abacf1989a4a63f1668520a.jpg",
                "https://i.pinimg.com/736x/8d/ae/91/8dae916a2758bed7318d0aebf5621e00.jpg"
            )
        ),
        MovieData(
            id = "6",
            title = "Salaar: Part 1 – Ceasefire",
            year = "2023",
            genre = "Action, Thriller, Drama",
            director = "Prashanth Neel",
            actors = listOf("Prabhas", "Prithviraj Sukumaran", "Shruti Haasan", "Jagapathi Babu"),
            plot = "A gang leader makes a promise to a dying friend and takes on other criminal gangs.",
            poster = "https://i.pinimg.com/736x/86/63/81/866381b4c364611463751888832eb7c4.jpg",
            imdbRating = "6.5",
            images = listOf(
                "https://i.pinimg.com/736x/7e/ff/e0/7effe0ed695813b5561d0805d9139440.jpg",
                "https://i.pinimg.com/1200x/df/a2/71/dfa271537f2992a5dddade922fcbe6fd.jpg",
                "https://i.pinimg.com/736x/22/c7/a9/22c7a922b264e63b3d6cb749712fecf7.jpg"
            )
        ),
        MovieData(
            id = "7",
            title = "Guntur Kaaram",
            year = "2024",
            genre = "Action, Drama",
            director = "Trivikram Srinivas",
            actors = listOf("Mahesh Babu", "Sreeleela", "Meenakshi Chaudhary", "Ramya Krishna"),
            plot = "The king of the underworld in Guntur falls in love with a journalist working to expose the illegal activities in the city.",
            poster = "https://i.pinimg.com/736x/a9/75/bb/a975bb27eda9445771b2e2dbb461b815.jpg",
            imdbRating = "5.6",
            images = listOf(
                "https://i.pinimg.com/1200x/c5/f4/9d/c5f49dd6bb9516acc9e7d84c97dee358.jpg",
                "https://i.pinimg.com/736x/a8/27/22/a82722de914ae4399c5a43d6340ad694.jpg",
                "https://i.pinimg.com/1200x/15/3b/98/153b98f4bbf4cca9fd1810273ef68403.jpg"
            )
        ),
        MovieData(
            id = "8",
            title = "Hi Nanna",
            year = "2023",
            genre = "Drama, Romance",
            director = "Shouryuv",
            actors = listOf("Nani", "Mrunal Thakur", "Kiara Khanna"),
            plot = "A single father begins to narrate the story of his missing wife to his child, with nothing but the truth.",
            poster = "https://i.pinimg.com/736x/f6/94/4e/f6944ea739ff53b1b5766abfbc5f4c24.jpg",
            imdbRating = "8.2",
            images = listOf(
                "https://i.pinimg.com/736x/74/46/62/744662eac4d03017c6badf7e4b8d1db8.jpg",
                "https://i.pinimg.com/736x/82/92/a7/8292a7df051448a5a253955240ba06d7.jpg",
                "https://i.pinimg.com/736x/2e/51/98/2e51983401b11e770a2600a877cca2b0.jpg"
            )
        ),
        MovieData(
            id = "9",
            title = "Dasara",
            year = "2023",
            genre = "Action, Drama, Thriller",
            director = "Srikanth Odela",
            actors = listOf("Nani", "Keerthy Suresh", "Dheekshith Shetty"),
            plot = "In the backdrop of Singareni coal mines near Godavarikhani of Telangana, some dark secrets and a dispute between two friends are unveiled.",
            poster = "https://i.pinimg.com/736x/2e/51/98/2e51983401b11e770a2600a877cca2b0.jpg",
            imdbRating = "6.8",
            images = listOf(
                "https://i.pinimg.com/1200x/e3/46/61/e346611253ccc0706ec1eba4e1b71a40.jpg",
                "https://i.pinimg.com/1200x/e5/04/f3/e504f34d8ad70c7ee8d014d24895183c.jpg",
                "https://i.pinimg.com/736x/bd/64/39/bd6439117bb3b4087361586b6f774003.jpg"
            )
        ),
        MovieData(
            id = "10",
            title = "Virupaksha",
            year = "2023",
            genre = "Action, Mystery, Thriller",
            director = "Karthik Dandu",
            actors = listOf("Sai Dharam Tej", "Samyuktha Menon", "Sunil"),
            plot = "Mysterious deaths occur in a village due to an unknown person's occult practices. The whole village is scared, and the problems continue.",
            poster = "https://i.pinimg.com/736x/b2/a0/e2/b2a0e242a886fd872e0dc473aa5a3d30.jpg",
            imdbRating = "7.2",
            images = listOf(
                "https://i.pinimg.com/1200x/cb/2a/48/cb2a486e47275d2c467a1220b90aec35.jpg",
                "https://i.pinimg.com/1200x/77/0a/81/770a8160cf852dbe2cd5016cb01e282f.jpg",
                "https://i.pinimg.com/736x/1a/b7/39/1ab7399051149d59bc4cdcefc37ea7b7.jpg"
            )
        )
    )
}
