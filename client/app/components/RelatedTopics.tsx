"use client"
import React from 'react';

const RelatedTopics = () => {
  // Sample data for related questions (replace with dynamic data if needed)
  const relatedQuestions = [
    'How to set up a REST API with Node.js and Express?',
    'What are the best practices for error handling in Spring Boot?',
    'How to connect a Node.js application to a MongoDB database?',
    'What is the difference between @RestController and @Controller in Spring Boot?',
    'How to implement JWT authentication in a Node.js application?',
    'How to configure CORS in a Spring Boot application?',
    'What are the benefits of using NestJS over Express for Node.js applications?',
    'How to create custom middleware in a Node.js app?',
    'What is the purpose of the application.properties file in Spring Boot?',
    'How to deploy a Spring Boot application to AWS?',
  ];
  

  return (
    <div className="flex flex-col pc:w-[30%] max-pc:w-full max-pc:px-5 h-fit bg-base-100 p-5 rounded-lg shadow-md">
      <h3 className="text-lg font-semibold text-secondary mb-4">Related Topics</h3>
      <ul className="flex flex-col gap-3">
        {relatedQuestions.map((title, index) => (
          <li key={index} className="text-sm font-medium text-secondary text-opacity-70 hover:text-opacity-100 hover:underline">
            <a href="#">{title}</a>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default RelatedTopics;
