<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>
<%
    Random random = new Random();

    // Generate random numbers for 5 questions
    int numQuestions = 5;
    int[] num1 = new int[numQuestions];
    int[] num2 = new int[numQuestions];

    for (int i = 0; i < numQuestions; i++) {
        num1[i] = random.nextInt(10) + 1; // Generate numbers between 1 and 10
        num2[i] = random.nextInt(10) + 1;
    }

    // Check if form has been submitted
    String submitted = request.getParameter("submitted");

    if (submitted == null) {
        // Display the quiz
%>
        <html>
        <head><title>Addition Quiz</title></head>
        <body>
        <h2>Addition Quiz</h2>
        <form method="post" action="quiz.jsp">
            <%
                for (int i = 0; i < numQuestions; i++) {
            %>
                    <p><%= num1[i] %> + <%= num2[i] %> = 
                    <input type="number" name="answer<%= i %>" required></p>
                    <!-- Store the numbers as hidden fields to process later -->
                    <input type="hidden" name="num1<%= i %>" value="<%= num1[i] %>">
                    <input type="hidden" name="num2<%= i %>" value="<%= num2[i] %>">
            <%
                }
            %>
            <input type="hidden" name="submitted" value="true">
            <input type="submit" value="Submit Answers">
        </form>
        </body>
        </html>
<%
    } else {
        // Process the answers and calculate the score
        int score = 0;
        for (int i = 0; i < numQuestions; i++) {
            int correctAnswer = Integer.parseInt(request.getParameter("num1" + i)) +
                                Integer.parseInt(request.getParameter("num2" + i));
            int userAnswer = Integer.parseInt(request.getParameter("answer" + i));

            if (userAnswer == correctAnswer) {
                score++;
            }
        }

        // Display the result
%>
        <html>
        <head><title>Quiz Results</title></head>
        <body>
        <h2>Your Results</h2>
        <p>You got <%= score %> out of <%= numQuestions %> correct!</p>
        <p><a href="quiz.jsp">Try Again</a></p>
        </body>
        </html>
<%
    }
%>
