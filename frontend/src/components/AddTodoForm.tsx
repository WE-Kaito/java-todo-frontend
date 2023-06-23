import {useState} from "react";
import axios from "axios";
import {AddForm} from "./styles.ts";

export default function AddTodoForm(props: any) {

    const [todoDescription, setTodoDescription] = useState<string>("");

    function addTodo(){
        axios.post("/api/todo", {description: todoDescription, status: "OPEN"})
        console.log(props.todos);
    }

    function formHandler(e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        addTodo();
        e.currentTarget.reset();
    }

    return (
        <AddForm onSubmit={formHandler}>
            <textarea rows={3} value={todoDescription} onChange={(e) => setTodoDescription(e.target.value)}/>
            <button type="submit" >Add</button>
        </AddForm>
    );
}
