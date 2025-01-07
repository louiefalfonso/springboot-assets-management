import './App.css'
import { Route, Routes } from 'react-router-dom'
import Dashboard from './pages/Dashboard'

function App() {
  
  return (
    <>
    <main className="w-full min-h-screen bg-[#f3f4f6] ">
      <Routes>
        <Route path="/" element={<Dashboard />} />
      </Routes>
    </main> 
    </>
  )
}

export default App
