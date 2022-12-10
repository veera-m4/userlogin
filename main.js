  
  // function to emulate question loading from backend
  async function fetchQuestions() {
    
    return await axios.get('http://localhost:8080/getquestions');
  }
  
  function getQuestions() {
    fetchQuestions().then((response) => {
      console.log(response) 
      const allQuestions = response.data;
      function displayQuestion(index) {
        if (index >= allQuestions.length) 
        {
          window.location = "sucess.html"
        }
  
        const currentQuestion = allQuestions[index];
  
        const questionElement = document.getElementById("question");
        questionElement.innerText = currentQuestion;
  
        const answerInput = document.getElementById("answer-input");
        const submitButton = document.getElementById("submit-button");
        submitButton.addEventListener("click", () => {
          const answer = answerInput.value;
          console.log(answer);
          axios.post("http://localhost:8080/check", {
            question: currentQuestion,
            answer
            }).then((response) => {
              console.log(response);
            if(response.data === true)
            {
              const q = document.createElement('p')
              q.innerText = currentQuestion;
              document.querySelector('#prev').appendChild(q);
              displayQuestion(index + 1);
            }
            else{
              window.location = "fail.html"
            }
            // put response logic here
            
          });
        });
      }
  
      displayQuestion(0);
    });
  }
  
  getQuestions();