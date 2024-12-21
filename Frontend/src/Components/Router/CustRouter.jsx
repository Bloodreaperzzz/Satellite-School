import React from 'react'
import Navbar from '../Navbar/Navbar'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import Home from '../Home/Home'
import Campuses from '../Campuses/Campuses'
import AcademicDetails from '../Student/AcademicDetails'
import NonAcad from '../Student/NonAcad'
import LoginPage from '../login/LoginPage'
import StudentSignup from '../login/StudentSignUp'
import SignupPage from '../login/SignUpPage'
import AddGuardian from '../Student/AddGuardian'
import TransportWorker from '../Worker/TransportWorker'
import TeacherDashboard from '../Teacher/TeacherDashboard'
import HomePage from '../FounderManager/HomePage'
import TeacherRequests from '../FounderManager/TeacherRequests'
import StudentRequests from '../FounderManager/StudentRequests'
import StudentForm from '../FounderManager/StudentForm'
import TeacherSignUpPage from '../login/TeacherSignUpPage'

const CustRouter = () => {
  return (
    <div>
      
      
      <Routes>
            <Route path='/' element={<Home/>}/>
            <Route path='/campuses' element={<Campuses/>}/>
            <Route path='/student/academic' element={<AcademicDetails/>}/>
            <Route path='/student/nonacademic' element={<NonAcad/>}/>
            <Route path='/login' element={<LoginPage/>}/>
            <Route path='/signup' element={<SignupPage/>}/>
            <Route path='/signup/student' element={<StudentSignup/>}/>
            <Route path='/signup/teacher' element={<TeacherSignUpPage/>}/>
            <Route path='/student/addguardian' element={<AddGuardian/>}/>
            <Route path='/worker' element={<TransportWorker/>}/>
            <Route path='/teacher' element={<TeacherDashboard/>}/>
            <Route path='/founder' element={<HomePage/>}/>
            <Route path='/founder/teachers' element={<TeacherRequests/>}/>
            <Route path='/founder/students' element={<StudentRequests/>}/>
            <Route path='founder/students/:aadharNumber' element={<StudentForm/>}/>
      </Routes>
      
      
    </div>
  )
}

export default CustRouter;