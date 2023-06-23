import './App.css'
import TodoBoard from "./components/TodoBoard.tsx";
import {useEffect, useState} from "react";
import axios from "axios";
import AddTodoForm from "./components/AddTodoForm.tsx";

export type Todo = {id: string, description: string, status: string};
function App() {

    const [todos , setTodos] = useState<Todo[]>([]);
    const [highlightedTodo, setHighlightedTodo] = useState<Todo | null>(null);

    function getTodos() {
        axios
            .get("/api/todo")
            .then((res) => res.data)
            .catch((err) => {
                console.error(err);
            })
            .then((data) => {
                setTodos(data);
            });
    }

    useEffect(() => {
        getTodos()
    }, [todos]);

    function getTodo(todoId: string) {
        axios.get(`/api/todo/${todoId}`).then((res) => res.data)
            .then((data) => {
                if (todoId === highlightedTodo?.id) {
                    setHighlightedTodo(null);
                    return;
                }
                setHighlightedTodo(data)});
    }

  return (
    <>
      <h1>To-Do Board</h1>

        <TodoBoard todos={todos} getTodo={getTodo} hltd={highlightedTodo}/>

        <AddTodoForm todos={todos}/>
    </>
  )
}

export default App
