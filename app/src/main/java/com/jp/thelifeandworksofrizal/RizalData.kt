package com.jp.thelifeandworksofrizal

object RizalData {

    private fun q(
        question: String,
        a: String,
        b: String,
        c: String,
        d: String,
        answer: String
    ) = QuizQuestion(
        question = question,
        options = listOf(a, b, c, d),
        correctAnswer = answer
    )

    val book = RizalBook(
        title = "The Life and Works of Jose Rizal",
        chapters = listOf(
            RizalChapter(
                id = 1,
                title = "Chapter 1: Early Life and Family Background",
                subtitle = "Calamba, family values, and childhood influences",
                content = """
                    Jose Protacio Rizal Mercado y Alonso Realonda was born on June 19, 1861, in Calamba, Laguna. He came from a well-respected family that valued education, discipline, faith, and hard work. His father, Francisco Mercado, was a tenant farmer, while his mother, Teodora Alonso, was known for her intelligence, strong character, and love for learning. Rizal grew up in a home where reading, proper behavior, and self-improvement were encouraged every day.
                    
                    During his childhood, Rizal already showed signs of brilliance. He enjoyed reading books, drawing, sculpting, and writing poems. His older brother Paciano played an important role in guiding him, while his mother became his first teacher. The peaceful environment of Calamba, together with the social conditions he saw around him, helped shape his awareness of injustice and his growing love for the country.
                    
                    These early years were important because they formed Rizal's moral values and intellectual curiosity. His childhood experiences taught him discipline, compassion, and a strong sense of purpose. Even before he became a national hero, Rizal's life already showed the qualities of a thinker, artist, and future reformist.
                """.trimIndent()
            ),
            RizalChapter(
                id = 2,
                title = "Chapter 2: Education and Intellectual Growth",
                subtitle = "Biñan, Ateneo, UST, and studies in Europe",
                content = """
                    Rizal's education began at home under the guidance of his mother and private tutors. He later studied in Biñan, Laguna, where he experienced formal schooling outside his home. Although he was still young, he quickly proved that he was more advanced than many of his classmates. He developed discipline, confidence, and a strong desire to excel in academics.
                    
                    He continued his studies at the Ateneo Municipal de Manila, where he graduated with excellent performance. At Ateneo, Rizal developed not only his academic abilities but also his leadership, writing, and artistic skills. He then enrolled at the University of Santo Tomas, where he studied medicine. However, he became dissatisfied with the treatment of Filipino students and the limitations he experienced there.
                    
                    Because of this, Rizal continued his education in Spain and later in other parts of Europe. He studied medicine, philosophy, and languages, and he trained further in ophthalmology partly because he wanted to help treat his mother's failing eyesight. His education abroad broadened his perspective and exposed him to liberal ideas, reform movements, and the value of intellectual freedom.
                """.trimIndent()
            ),
            RizalChapter(
                id = 3,
                title = "Chapter 3: Travels Abroad and Reform Advocacy",
                subtitle = "Europe, the Propaganda Movement, and peaceful reform",
                content = """
                    Rizal's travels abroad became a major part of his development as a reformist. While living in Spain, France, Germany, and other countries, he met fellow Filipinos and intellectuals who shared concerns about the condition of the Philippines under Spanish colonial rule. These experiences deepened his understanding of politics, society, and national identity.
                    
                    Rizal became involved in the Propaganda Movement, a peaceful campaign that called for reforms such as equal treatment of Filipinos, representation in the Spanish Cortes, freedom of speech, and better government. Instead of supporting violence, Rizal believed that education, civic awareness, and responsible reform were the most powerful tools for national progress.
                    
                    Through essays, letters, speeches, and participation in reform circles, Rizal helped awaken national consciousness among Filipinos. His travels allowed him to compare different societies and recognize what his homeland lacked. This period strengthened his mission to use knowledge and writing in the service of justice and national dignity.
                """.trimIndent()
            ),
            RizalChapter(
                id = 4,
                title = "Chapter 4: Life and Works of Rizal",
                subtitle = "Novels, essays, poems, and nationalist thought",
                content = """
                    Rizal's literary works are among his greatest contributions to Philippine history. His most famous novels, Noli Me Tangere and El Filibusterismo, exposed the abuses of colonial officials, friars, and a corrupt social system. These works were not written merely for entertainment. They were meant to reveal truth, challenge injustice, and encourage Filipinos to think critically about their condition.
                    
                    Aside from novels, Rizal also wrote essays, poems, annotations, letters, and articles. One of his most admired poems is Mi Ultimo Adios, which expressed his love for the Philippines and his willingness to sacrifice for it. His writings reflected intelligence, sensitivity, patriotism, and moral courage. He used literature as a peaceful but powerful weapon against oppression.
                    
                    Rizal's works helped inspire nationalism in the Philippines. They encouraged readers to value freedom, dignity, reason, and reform. Because of his writings, Rizal became not only a brilliant author but also one of the strongest voices of Filipino identity and awakening.
                """.trimIndent()
            ),
            RizalChapter(
                id = 5,
                title = "Chapter 5: Exile, Trial, Death, and Legacy",
                subtitle = "Dapitan, martyrdom, and lasting national influence",
                content = """
                    Rizal was exiled to Dapitan in Mindanao, where he spent several productive years serving the local community. Even in exile, he did not waste his time. He worked as a doctor, teacher, engineer, and community leader. He improved the lives of many people through medicine, education, and civic projects, proving that his love for the country was shown through service as well as writing.
                    
                    Despite his peaceful advocacy, Rizal was later arrested, brought back to Manila, and accused of crimes related to rebellion. He was tried by the Spanish authorities and sentenced to death. On December 30, 1896, he was executed at Bagumbayan. His death shocked and inspired many Filipinos, becoming a symbol of sacrifice and patriotism.
                    
                    Today, Rizal is remembered as the national hero of the Philippines. His life and works continue to be studied because they represent intelligence, courage, discipline, and love of country. His legacy lives on in schools, monuments, books, and in the continuing effort of Filipinos to build a just and honorable nation.
                """.trimIndent()
            )
        )
    )

    val chapterQuizzes = listOf(
        ChapterQuiz(
            chapterId = 1,
            chapterTitle = "Chapter 1: Early Life and Family Background",
            questions = listOf(
                q("Where was Jose Rizal born?", "Calamba, Laguna", "Dapitan", "Manila", "Cebu", "Calamba, Laguna"),
                q("When was Rizal born?", "June 19, 1861", "December 30, 1896", "June 12, 1898", "July 7, 1892", "June 19, 1861"),
                q("Who was Rizal's mother?", "Teodora Alonso", "Josephine Bracken", "Leonor Rivera", "Trinidad Rizal", "Teodora Alonso"),
                q("Who was Rizal's father?", "Francisco Mercado", "Paciano Rizal", "Marcelo del Pilar", "Andres Bonifacio", "Francisco Mercado"),
                q("How many children were in Rizal's family?", "11", "7", "9", "13", "11"),
                q("Who became Rizal's first teacher?", "His mother", "His brother", "His priest", "His doctor", "His mother"),
                q("Which brother greatly guided Rizal in his youth?", "Paciano", "Saturnina", "Narcisa", "Lucia", "Paciano"),
                q("What talent did Rizal show as a child?", "Writing and drawing", "Shipbuilding", "Law practice", "Military combat", "Writing and drawing"),
                q("What values were strong in Rizal's family?", "Education and discipline", "Luxury and politics", "Trade and war", "Mining and farming only", "Education and discipline"),
                q("What helped shape Rizal's early patriotism?", "His childhood experiences and family values", "His military training", "Royal sponsorship", "Foreign citizenship", "His childhood experiences and family values")
            )
        ),
        ChapterQuiz(
            chapterId = 2,
            chapterTitle = "Chapter 2: Education and Intellectual Growth",
            questions = listOf(
                q("Where did Rizal study before going to Manila?", "Biñan, Laguna", "Cebu City", "Baguio", "Dapitan", "Biñan, Laguna"),
                q("Which school in Manila helped Rizal excel academically?", "Ateneo Municipal de Manila", "Far Eastern University", "De La Salle College", "Lyceum of the Philippines", "Ateneo Municipal de Manila"),
                q("What course did Rizal pursue that was related to helping his mother?", "Medicine", "Architecture", "Engineering", "Journalism", "Medicine"),
                q("Which university in Manila did Rizal attend after Ateneo?", "University of Santo Tomas", "University of the Philippines", "Colegio de San Juan", "University of San Carlos", "University of Santo Tomas"),
                q("Why did Rizal continue studying abroad?", "To broaden his knowledge and opportunities", "To become a soldier", "To avoid reading", "To open a business first", "To broaden his knowledge and opportunities"),
                q("In which country did Rizal continue higher studies in Europe?", "Spain", "Japan", "Mexico", "Brazil", "Spain"),
                q("What specialty did Rizal further study in Europe?", "Ophthalmology", "Dentistry", "Veterinary medicine", "Marine biology", "Ophthalmology"),
                q("What did Rizal develop strongly through education?", "Critical thinking and reformist ideas", "Interest in warfare", "Love for luxury", "Desire for conquest", "Critical thinking and reformist ideas"),
                q("Aside from medicine, Rizal also studied what fields?", "Philosophy and languages", "Agriculture and mining", "Aviation and coding", "Navigation and military science", "Philosophy and languages"),
                q("What was one reason Rizal became dissatisfied at UST?", "Unequal treatment and academic limitations", "The school had no classes", "He disliked books", "He wanted only sports", "Unequal treatment and academic limitations")
            )
        ),
        ChapterQuiz(
            chapterId = 3,
            chapterTitle = "Chapter 3: Travels Abroad and Reform Advocacy",
            questions = listOf(
                q("What movement did Rizal join for peaceful reforms?", "Propaganda Movement", "Katipunan Army", "Guardia Civil", "Revolutionary Council", "Propaganda Movement"),
                q("What kind of reforms did Rizal support?", "Peaceful and civic reforms", "Violent revenge", "Foreign takeover", "Military dictatorship", "Peaceful and civic reforms"),
                q("What did Rizal use as a major weapon against oppression?", "Writing and ideas", "Cannons", "Secret weapons", "Trade sanctions", "Writing and ideas"),
                q("What did the reformists want for Filipinos?", "Equal rights and representation", "Total isolation", "Removal of education", "Higher tribute only", "Equal rights and representation"),
                q("What did Rizal gain from traveling in Europe?", "A broader understanding of society and politics", "A royal title", "A military command", "A shipping company", "A broader understanding of society and politics"),
                q("What newspaper was associated with the reform movement?", "La Solidaridad", "The Manila Times", "Diario de Dapitan", "El Comercio Libre", "La Solidaridad"),
                q("How did Rizal view education?", "As a powerful tool for national progress", "As unnecessary for society", "As useful only for the rich", "As less important than war", "As a powerful tool for national progress"),
                q("What did Rizal compare during his travels?", "Different societies and governments", "Only food and fashion", "Only church buildings", "Only military camps", "Different societies and governments"),
                q("Which of these best describes Rizal's advocacy?", "Intellectual, peaceful, and reform-oriented", "Violent and destructive", "Secretive and selfish", "Commercial and profit-driven", "Intellectual, peaceful, and reform-oriented"),
                q("What did Rizal hope to awaken among Filipinos?", "National consciousness", "Fear of books", "Obedience to abuse", "Dependence on colonizers", "National consciousness")
            )
        ),
        ChapterQuiz(
            chapterId = 4,
            chapterTitle = "Chapter 4: Life and Works of Rizal",
            questions = listOf(
                q("Which novel exposed abuses under Spanish rule?", "Noli Me Tangere", "Florante at Laura", "Ibong Adarna", "Dekada '70", "Noli Me Tangere"),
                q("Which novel is the sequel to Noli Me Tangere?", "El Filibusterismo", "Mi Ultimo Adios", "La Solidaridad", "Sa Aking Mga Kabata", "El Filibusterismo"),
                q("What kind of work is Mi Ultimo Adios?", "Poem", "Novel", "Play", "Newspaper", "Poem"),
                q("Why did Rizal write his novels?", "To expose injustice and awaken Filipinos", "To entertain colonizers only", "To sell luxury books", "To avoid public issues", "To expose injustice and awaken Filipinos"),
                q("What tone does El Filibusterismo have compared to Noli?", "Darker and more serious", "More humorous only", "Completely romantic", "More childish", "Darker and more serious"),
                q("Aside from novels, Rizal also wrote what?", "Essays, letters, and articles", "Only songs", "Only laws", "Only newspaper ads", "Essays, letters, and articles"),
                q("What was one effect of Rizal's writings?", "They inspired nationalism", "They ended all wars instantly", "They removed all taxes immediately", "They made him a king", "They inspired nationalism"),
                q("What did Rizal use literature for?", "To fight oppression peacefully", "To promote gambling", "To avoid social issues", "To support corruption", "To fight oppression peacefully"),
                q("Which pair are Rizal's two most famous novels?", "Noli Me Tangere and El Filibusterismo", "Ibong Adarna and Biag ni Lam-ang", "Florante at Laura and Banaag at Sikat", "Famous Five and Tom Sawyer", "Noli Me Tangere and El Filibusterismo"),
                q("How is Rizal remembered in literature?", "As a major voice of Filipino nationalism", "As a foreign conqueror", "As a businessman only", "As a military dictator", "As a major voice of Filipino nationalism")
            )
        ),
        ChapterQuiz(
            chapterId = 5,
            chapterTitle = "Chapter 5: Exile, Trial, Death, and Legacy",
            questions = listOf(
                q("Where was Rizal exiled?", "Dapitan", "Calamba", "Biñan", "Bohol", "Dapitan"),
                q("What roles did Rizal perform in Dapitan?", "Doctor, teacher, and community leader", "General, governor, and mayor", "Judge, sailor, and actor", "Banker, pilot, and soldier", "Doctor, teacher, and community leader"),
                q("When was Rizal executed?", "December 30, 1896", "June 19, 1861", "August 21, 1983", "June 12, 1898", "December 30, 1896"),
                q("Where was Rizal executed?", "Bagumbayan", "Dapitan Plaza", "Intramuros Harbor", "Calamba Churchyard", "Bagumbayan"),
                q("What did Rizal continue to do while in exile?", "Serve the community", "Lead a royal court", "Abandon his principles", "Leave the Philippines permanently", "Serve the community"),
                q("How did many Filipinos view Rizal's death?", "As a symbol of sacrifice and patriotism", "As a simple private event", "As a business matter", "As a school holiday only", "As a symbol of sacrifice and patriotism"),
                q("What happened after Rizal was arrested?", "He was tried and sentenced to death", "He was made governor", "He was sent to Europe again", "He retired immediately", "He was tried and sentenced to death"),
                q("What does Rizal's legacy continue to influence?", "Filipino identity and patriotism", "European monarchy", "Ancient mythology", "International banking", "Filipino identity and patriotism"),
                q("Why is Rizal considered a national hero?", "Because of his life, writings, and sacrifice", "Because he ruled the country as king", "Because he discovered gold", "Because he built a navy", "Because of his life, writings, and sacrifice"),
                q("Which best describes Rizal's final legacy?", "A model of intelligence, service, and love of country", "A symbol of greed", "A forgotten traveler", "A ruler of Spain", "A model of intelligence, service, and love of country")
            )
        )
    )

    val reportSections = listOf(
        ReportSection(
            title = "Introduction",
            content = """
                Jose Rizal is one of the most important figures in Philippine history. He is remembered not only because he became the national hero, but also because of the way he used education, literature, and peaceful reform as tools for national awakening. Studying his life and works helps students understand the roots of Filipino nationalism and the value of civic responsibility.
            """.trimIndent()
        ),
        ReportSection(
            title = "Early Life and Education",
            content = """
                Rizal's family background and early education played a major role in his development. His mother introduced him to reading and discipline, while his formal schooling in Biñan, Ateneo, UST, and Europe expanded his intellectual capacity. His educational journey shaped his critical thinking and prepared him to become a reformist writer and public intellectual.
            """.trimIndent()
        ),
        ReportSection(
            title = "Works and Reform Advocacy",
            content = """
                Through works such as Noli Me Tangere and El Filibusterismo, Rizal exposed the abuses of colonial rule and encouraged Filipinos to think critically. He believed in reform through education, reason, and civic participation. His essays, letters, and poems strengthened the reform movement and contributed to the growth of Filipino national consciousness.
            """.trimIndent()
        ),
        ReportSection(
            title = "Conclusion",
            content = """
                Rizal's life proves that love of country can be expressed through knowledge, discipline, and service. His legacy remains relevant because his ideas on justice, education, identity, and moral courage still speak to modern society. Learning about Rizal is not only about remembering history, but also about applying those values in the present.
            """.trimIndent()
        )
    )

    val members = listOf(
        MemberInfo("Tolop, John Paul", "Project Lead, Backend Developer"),
        MemberInfo("Mabansag, Rolando Jr.", "UI Designer, Front-end Developer"),
        MemberInfo("Crisostomo, Roger", "Lead Writer"),
        MemberInfo("Dabocol, Dexter", "Presenter"),
        MemberInfo("Bardaje, Jana June", "Researcher"),
        MemberInfo("Delacruz, Prince Dale", "Researcher")
    )
}