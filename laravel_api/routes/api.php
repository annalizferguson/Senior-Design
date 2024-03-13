<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::get('customers', [App\Http\Controllers\CustomerController::class, 'index']);
Route::post('customers', [App\Http\Controllers\CustomerController::class, 'store']);
Route::delete('delete_customer/{id}', [App\Http\Controllers\CustomerController::class, 'deleteCustomer']);