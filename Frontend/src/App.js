import logo from './logo.svg';
import './App.css';
import Home from './Components/Home/Home';
import Navbar from './Components/Navbar/Navbar';
import Campuses from './Components/Campuses/Campuses';
import CustRouter from './Components/Router/CustRouter';

function App() {
  return (
    <div className="App">
      <CustRouter/>
    </div>
  );
}

export default App;
