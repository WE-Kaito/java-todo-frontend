import styled from "styled-components";


// TodoCard.tsx
export const CardContainer = styled.div<{ status: string, todo: string, hltd: string | null }>`
  width: 20em;
  height: 12em;
  
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  
  border: 1px solid black;
  border-radius: 15px;
  filter: drop-shadow(0px 0px 1px ghostwhite);
  
  background: ${(props) => props.todo === props.hltd ? "crimson" : "#747bff"};

  grid-column-start: ${(props) => props.status === "OPEN" ? 1 : props.status === "IN_PROGRESS" ? 2 : 3};
`

export const CardButtonContainer = styled.div`
  display: flex;
  justify-content: space-between;
`


// TodoBoard.tsx

export const BoardContainer = styled.div`
display: grid;
grid-template-columns: repeat(3, 1fr);
grid-gap: 2em;
justify-items: center;
align-items: center;
`

// AddTodoForm.tsx

export const AddForm = styled.form`
width: 20em;
height: fit-content;
  display: flex;
  gap: 10px;
flex-direction: column;
justify-content: center;
`