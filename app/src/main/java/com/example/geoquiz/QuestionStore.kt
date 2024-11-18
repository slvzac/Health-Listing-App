package com.example.geoquiz

object QuestionStore {

    const val USERNAME: String = "user_name"
    const val QUESTIONS_TOTAL: String = "total_questions"
    const val ANSWER_CORRECT: String = "correct_answers"
    const val CHEAT_TOKENS: String = "cheat_tokens"

    fun getQuestions(): ArrayList<QuestionStructure> {
        val questionsList = ArrayList<QuestionStructure>()

// 1.
        val que1 = QuestionStructure(
            1, "What country does this flag belong to?",
            R.drawable.ques_japan,
            "China",
            "Japan",
            "South Korea",
            "Thailand",
            2
        )
        questionsList.add(que1)

// 2.
        val que2 = QuestionStructure(
            2, "What is the capital of Australia?",
            R.drawable.ques_australia,
            "Sydney",
            "Melbourne",
            "Canberra",
            "Perth",
            3
        )
        questionsList.add(que2)

// 3.
        val que3 = QuestionStructure(
            3, "Which country is known as the Land of the Rising Sun?",
            R.drawable.ques_sun,
            "Japan",
            "India",
            "Thailand",
            "South Korea",
            1
        )
        questionsList.add(que3)

// 4.
        val que4 = QuestionStructure(
            4, "What country does this flag belong to?",
            R.drawable.ques_brazil,
            "Argentina",
            "Brazil",
            "Portugal",
            "Mexico",
            2
        )
        questionsList.add(que4)

// 5.
        val que5 = QuestionStructure(
            5, "What is the largest continent by area?",
            R.drawable.icon_earth,
            "Africa",
            "Asia",
            "North America",
            "Europe",
            2
        )
        questionsList.add(que5)

// 6.
        val que6 = QuestionStructure(
            6, "What country does this flag belong to?",
            R.drawable.ques_italy,
            "Spain",
            "Ireland",
            "Italy",
            "France",
            3
        )
        questionsList.add(que6)

// 7.
        val que7 = QuestionStructure(
            7, "What is the capital of Canada?",
            R.drawable.ques_canada,
            "Toronto",
            "Vancouver",
            "Ottawa",
            "Montreal",
            3
        )
        questionsList.add(que7)

// 8.
        val que8 = QuestionStructure(
            8, "Which country is home to the Great Barrier Reef?",
            R.drawable.ques_corals,
            "Australia",
            "New Zealand",
            "Fiji",
            "Indonesia",
            1
        )
        questionsList.add(que8)

// 9.
        val que9 = QuestionStructure(
            9, "What country does this flag belong to?",
            R.drawable.ques_russia,
            "Russia",
            "Poland",
            "Finland",
            "Sweden",
            1
        )
        questionsList.add(que9)

// 10.
        val que10 = QuestionStructure(
            10, "What is the highest mountain in the world?",
            R.drawable.ques_everest,
            "K2",
            "Kangchenjunga",
            "Mount Everest",
            "Lhotse",
            3
        )
        questionsList.add(que10)

// 11.
        val que11 = QuestionStructure(
            11, "What country does this flag belong to?",
            R.drawable.ques_southafrica,
            "South Africa",
            "Nigeria",
            "Kenya",
            "Ghana",
            1
        )
        questionsList.add(que11)

// 12.
        val que12 = QuestionStructure(
            12, "What is the longest river in the world?",
            R.drawable.ques_nile,
            "Amazon River",
            "Yangtze River",
            "Mississippi River",
            "Nile River",
            4
        )
        questionsList.add(que12)

// 13.
        val que13 = QuestionStructure(
            13, "What country does this flag belong to?",
            R.drawable.ques_germany,
            "Belgium",
            "Germany",
            "Netherlands",
            "Austria",
            2
        )
        questionsList.add(que13)

// 14.
        val que14 = QuestionStructure(
            14, "Which country is the smallest by land area?",
            R.drawable.ques_vaticancity,
            "Monaco",
            "San Marino",
            "Liechtenstein",
            "Vatican City",
            4
        )
        questionsList.add(que14)

// 15.
        val que15 = QuestionStructure(
            15, "What country does this flag belong to?",
            R.drawable.ques_sweden,
            "Denmark",
            "Sweden",
            "Norway",
            "Finland",
            2
        )
        questionsList.add(que15)

        return questionsList
    }
}
// END