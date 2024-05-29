import { isRouteErrorResponse, useRouteError } from "react-router-dom";
import styles from "./Error.module.scss";

const PostIdError = () => {
  const error = useRouteError();

  let errorMessage = "";
  if (error instanceof Error) {
    errorMessage = error.message;
  } else if (typeof error === "string") {
    errorMessage = error;
  }else if(isRouteErrorResponse(error)){
    errorMessage = `${error.data} ${error.status} ${error.statusText}`
  }

  return (
    <div className={styles.error}>
      <h1>Oops</h1>
      <p>Invalid Post id</p>
      <p>
        <i>{errorMessage}</i>
      </p>
    </div>
  );
};

export default PostIdError;
