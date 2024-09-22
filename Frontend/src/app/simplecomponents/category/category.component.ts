import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';

import { HttpErrorResponse } from '@angular/common/http';

import { Category } from '../../interfaces/category';
import { CategoryService } from '../../services/category.service';

@Injectable({
  providedIn : 'root'
})
@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  public categories: Category[] = [];
  public category = <Category>{ };

  constructor(private categoryService : CategoryService) {
  }

  ngOnInit(): void {
  }

  public getByIdCategory(id: number): void{
    this.categoryService.getById(id).subscribe(
      (response: Category) => {
        this.category = response;
      },
      (error : HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
