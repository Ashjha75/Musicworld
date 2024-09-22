import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';

import { HttpErrorResponse } from '@angular/common/http';

import { Category } from '../../interfaces/category';
import { CategoryService } from '../../services/category.service';

import { Router } from '@angular/router';

@Injectable({
  providedIn : 'root'
})
@Component({
  selector: 'app-allcategories',
  templateUrl: './allcategories.component.html',
  styleUrls: ['./allcategories.component.css']
})
export class AllcategoriesComponent implements OnInit {

  public categories: Category[] = [];

  constructor(private categoryService : CategoryService,
              private router : Router) {
    this.getCategories();
  }

  ngOnInit(): void {
  }

  public gotocategory(id: number): void{
    this.router.navigate(['/category/' + id]);
  }

  public getCategories(): void{
    this.categoryService.getAll().subscribe(
      (response: Category[]) => {
        this.categories = response;
        console.log(response);
      },
      (error : HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
