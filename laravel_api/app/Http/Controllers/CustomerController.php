<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Customer;

class CustomerController extends Controller
{
    public function index()
    {
        $customers = Customer::all();
        return response()->json(['customers' => $customers]);
    }

    public function store(Request $request)
    {
        $customer = new Customer();
        $customer->username = $request->username;
        $customer->password = $request->password;
        $customer->firstname = $request->firstname;
        $customer->lastname = $request->lastname;
        $customer->ssn = $request->ssn;
        $customer->dob = $request->dob;
        $customer->email = $request->email;
        $customer->address = $request->address;
        $customer->mailing_address = $request->mailing_address;
        $customer->phone_number = $request->phone_number;
        $customer->cellphone_number = $request->cellphone_number;

        $customer->save();

        return response()->json([
            'message' => 'Customer created successfully',
            'customer' => $customer,
        ]);
    }

    public function deleteCustomer($id)
    {
        $customer = Customer::find($id);
        if($customer)
        {
            $customer->delete();
            return response()->json([
                'message' => 'Customer deleted successfully']);
        }
        else
        {
            return response()->json([
                'message' => "Customer with id:$id does not exist"]);
        }
    }
}
