import { Route, Routes } from 'react-router-dom'
import Dashboard from './pages/Dashboard'
import AssetsPage from './pages/AssetsPage'

function App() {
  
  return (
    <>
      <main className="w-full min-h-screen bg-[#f3f4f6] ">
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/assets" element={<AssetsPage/>} />
        </Routes>
      </main>
    </>
  );
}

export default App
