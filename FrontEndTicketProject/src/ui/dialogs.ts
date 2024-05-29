import Swal from "sweetalert2";

function success(message: string) {
  return Swal.fire({
    icon: "success",
    title: "success",
    text: message,
    showConfirmButton: true,
  });
}

function error(message: string | unknown = "something went wrong") {
  let msg = "something went wrong";
  if (
    message != null &&
    typeof message == "object" &&
    "message" in message &&
    typeof message.message == "string"
  ) {
    msg = message.message;
  }

  if (typeof message == "string") {
    msg = message;
  }

  return Swal.fire({
    icon: "error",
    title: "error",
    text: msg,
    showConfirmButton: true,
  });
}

export const Dialogs = { success, error };
