import {BoardContainer} from "./styles.ts";
import TodoCard from "./TodoCard.tsx";
import {Todo} from "../App.tsx";

type Props = {todos: Todo[], getTodo: (todoId: string) => void, hltd: Todo | null}
export default function TodoBoard(props: Props) {

    const {todos, getTodo, hltd} = props;


    return (
        <BoardContainer>
            <h2 style={{gridColumnStart:"1"}}>Open</h2>
            <h2 style={{gridColumnStart:"2"}}>In Progress</h2>
            <h2 style={{gridColumnStart:"3"}}>Done</h2>

            {todos?.map((todo,i) => (
                <TodoCard todo={todo} todos={todos} getTodo={getTodo} hltd={hltd} key={i} />
            ))}
        </BoardContainer>
    );
}