import { Route, Routes} from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import AssetsPage from "./pages/AssetsPage";
import AssetPage from "./pages/assets/[...id]/page";

function App() {
  
  return (
    <>
      <Routes>
        <Route path="/" element={<Dashboard/>} />
        <Route path="/assets" element={<AssetsPage/>} />
        <Route path="/assets/:id" element={<AssetPage/>} />
      
      </Routes>
    </>
  )
}

export default App
