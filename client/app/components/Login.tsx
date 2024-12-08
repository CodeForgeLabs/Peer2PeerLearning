"use client"
import React, { useState } from 'react'
import Typingeffect from './Typingeffect'
import Signup from './Signup'
import { signIn } from 'next-auth/react'
import { useSession } from 'next-auth/react'
import { useRouter } from 'next/navigation'


const Login = () => {
  const session = useSession()
  const router = useRouter()
  const [username , setUsername] = useState<string>('')
  const [password , setPassword] = useState<string>('')
  const [err , setErr] = useState('')
  const handleLogin = async () => { 
    console.log(username , password , "here")
    const res = await signIn('credentials', {
      redirect: false,
      username: username,
      password: password,
    })
    console.log(res)
    console.log(session)
    if (res?.ok){
      router.push("/")
    }
    else{
      
      
      setErr(res?.error  || " ")
    }
    
  }
  return (
    <div className="hero bg-base-200 min-h-screen">
  <div className="hero-content flex-col lg:flex-row-reverse">
    <div className="text-center lg:text-left">
 <Typingeffect />
      <p className="py-6">
        
      Log in to collaborate, share knowledge and build your reputation in the student learning community.
      </p>
    </div>
    <div className="card bg-base-100 w-full max-w-sm shrink-0 shadow-2xl">
    <form
  className="card-body"
  onSubmit={(e) => {
    e.preventDefault(); // Prevent default form submission
    handleLogin(); // Call your login function
  }}
>
  <div className="form-control">
    <label className="label">
      <span className="label-text">Username</span>
    </label>
    <input
      value={username}
      onChange={(e: React.ChangeEvent<HTMLInputElement>) => setUsername(e.target.value)}
      type="text"
      placeholder=""
      className="input input-bordered"
      required
    />
  </div>
  <div className="form-control">
    <label className="label">
      <span className="label-text">Password</span>
    </label>
    <input
      value={password}
      onChange={(e: React.ChangeEvent<HTMLInputElement>) => setPassword(e.target.value)}
      type="password"
      placeholder=""
      className="input input-bordered w-auto"
      required
    />
    <label className="label">
      <a href="#" className="label-text-alt link link-hover">Forgot password?</a>
    </label>
  </div>
  <p className="text-sm text-red-400">{err}</p>
  <div className="form-control mt-6">
    <button type="submit" className="btn btn-secondary">Login</button>
  </div>

  <p>
    Don&#39;t have an account?{' '}
    <span
      onClick={() => {
        console.log('here');
        const modal = document.getElementById('my_modal_3') as HTMLDialogElement;
        if (modal) {
          modal.showModal();
        }
      }}
      className="text-secondary underline"
    >
      Sign up
    </span>
  </p>
</form>

    </div>
  </div>
        <Signup />
</div>
  )
}

export default Login
