
import CredentialsProvider from "next-auth/providers/credentials"




export const options = {
  // Configure one or more authentication providers
  providers : [
    CredentialsProvider({
        name: "credentials",
        credentials: {
          username: { label: "username", type: "text" },
          password: { label: "password", type: "password" },
        },
        async authorize(credentials) {
          // credentials to backend for verification
         
          
          const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/auth/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              username : credentials?.username,
              password: credentials?.password,
            }),
            credentials: "include",
          });
  
          const user = await res.json();
          console.log("User: from the authorize body", user);
  
          // If no user or error, return null
          if (!res.ok || !user) {
            throw new Error(user?.message || user.err.email || "An error occurred");



          }
          

        
          
          return user
        },
      }),
  ],

  pages : {
    error: "/login", // Optional: Redirect to custom error page
  },
  
   
  callbacks : {
    //called whenever we check session fro the frontend
    async session({ session, token }) {
  
        session.email = token.username
        session.profilePicture = token.profilePicture
        return session;
    },
    //if the user is logged in, the token will be passed to the nextjs 
    //user comes from my backend
    async jwt({ token , user }) {
      if (user) {
        token.username = user.username;
      
        token.profilePicture = user.profilePicture;
      }
        return token;
      },
    
      
  }

}
