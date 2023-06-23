import {useState} from 'react';
import {CardContainer, CardButtonContainer, AddForm} from "./styles.ts";
import {Todo} from "../App.tsx";
import axios from "axios";

type Props = { todo: Todo, todos: Todo[], getTodo: (todoId: string) => void , hltd: Todo | null}
export default function TodoCard(props: Props) {

    const {todo, todos, getTodo, hltd} = props;

    const [editMode, setEditMode] = useState<boolean>(false);
    const [todoDescription, setTodoDescription] = useState<string>("");
    const [todoStatus, setTodoStatus] = useState<string>("");

    function advanceTodos() {
        const newTodos = [...todos];
        let newTd;
        newTodos.map((td) => {
            if (todo === td) {
                if (td.status === "OPEN") {
                    td.status = "IN_PROGRESS";
                } else if (todo.status === "IN_PROGRESS") {
                    td.status = "DONE";
                }
                newTd = td;
            }
            return td;
        });
        return newTd;
    }

    function sendAdvanceReq() {
        axios.put(`/api/todo/${todo.id}`, advanceTodos())
        setEditMode(false);
    }

    function sendUpdateReq() {
        axios.put(`/api/todo/${todo.id}`, {description: todoDescription, status: todoStatus});
        setEditMode(false);
    }

    function sendDeleteReq() {
        axios.delete(`/api/todo/${todo.id}`);
    }


    if (!editMode) {

        return (
            <CardContainer status={todo.status} hltd={hltd?.id} todo={todo.id}>
                <button onClick={()=> {getTodo(todo.id)}} style={{position:"absolute", right:"10px"}}>❗</button>
                <p>{todo.description}</p>
                <CardButtonContainer>
                    <button onClick={() => {
                        setEditMode(true)
                        setTodoDescription(todo.description);
                        setTodoStatus(todo.status);
                    }}>Edit
                    </button>
                    {todo.status !== "DONE" && <button onClick={sendAdvanceReq}>Advance</button>}
                    {todo.status === "DONE" && <button onClick={sendDeleteReq}>Delete</button>}
                </CardButtonContainer>
            </CardContainer>
        );
    } else {
        return (
            <CardContainer status={todo.status} hltd={hltd?.id} todo={todo.id}>
                <AddForm onSubmit={sendUpdateReq}>
                    <textarea rows={5} placeholder={todo.description} value={todoDescription}
                              onChange={(e) => setTodoDescription(e.target.value)}/>

                    <div style={{display: "flex", justifyContent: "space-evenly"}}>
                        <div>
                            <input type="radio" id="open" name="status" value="OPEN" checked={todoStatus === "OPEN"}
                                   onChange={() => setTodoStatus("OPEN")}/>
                            <label htmlFor="open">Open</label>
                        </div>

                        <div>
                            <input type="radio" id="in-progress" name="status" value="IN_PROGRESS"
                                   checked={todoStatus === "IN_PROGRESS"}
                                   onChange={() => setTodoStatus("IN_PROGRESS")}/>
                            <label htmlFor="in-progress">Doing</label>
                        </div>

                        <div>
                            <input type="radio" id="done" name="status" value="DONE" checked={todoStatus === "DONE"}
                                   onChange={() => setTodoStatus("DONE")}/>
                            <label htmlFor="done">Done</label>
                        </div>
                    </div>

                    <button type="submit">SAVE</button>
                </AddForm>
            </CardContainer>
        );
    }
}