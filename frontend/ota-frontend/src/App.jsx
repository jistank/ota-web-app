import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import CardSet from './components/CardSet/CardSet';
import cards from './components/CardSet/cards';


console.log(cards)

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div className='row'>
        <CardSet cards={cards}/>
        <CardSet cards={cards}/>
        <CardSet cards={cards}/>
        <CardSet cards={cards}/>
        <CardSet cards={cards}/>
      </div>
    </>
  )
}

export default App
