import type { FC } from "../@types/types";

export const Card: FC = (props) => {
  return (
    <div className="bg-white w-fit border-red-100 border p-5 text-slate-600 text-xl shadow-2xl rounded-lg">
      {props.children}
    </div>
  );
};
