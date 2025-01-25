import { Route, Routes } from 'react-router-dom'
import Dashboard from './pages/Dashboard'
import AssetsPage from './pages/AssetsPage'
import AssetPage from './pages/assets/[...id]';

function App() {
  
  return (
    <>
      <main className="w-full min-h-screen bg-[#f3f4f6] ">
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/assets" element={<AssetsPage />} />
          <Route path="/assets/:id" element={<AssetPage />} />
        </Routes>
      </main>
    </>
  );
}

export default App
