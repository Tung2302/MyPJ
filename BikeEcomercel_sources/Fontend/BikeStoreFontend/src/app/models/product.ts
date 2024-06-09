import { Category } from "./category";

export interface Product {
    id?: number;
    name?: string;
    cost?: number;
    size?: string;
    quantity?: number;
    description?: string;
    color?: string;
    image?: string;
    disable?: boolean;
    category?: Category;
}