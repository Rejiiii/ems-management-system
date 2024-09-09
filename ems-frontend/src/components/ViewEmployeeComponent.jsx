import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { viewEmployee } from '../services/EmployeeService';

function ViewEmployeeComponent() {

  const { id } = useParams();
  const [employee, setEmployee] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    viewEmployee(id).then((response) => {
      setEmployee(response.data);
    }).catch(error => {
      console.error(error);
    })
  }, [id])

  function goBack() {
    navigate('/employees');
  } 

  return (
    <div className='container'>
      <h2 className='text-center'>View Employee</h2>
      <div className='card'>
        <div className='card-body'>
          <h3 className='mb-3'>Employee ID: {employee.id}</h3>
          <p><strong>First Name: </strong>{employee.firstName}</p>
          <p><strong>Last Name: </strong>{employee.lastName}</p>
          <p><strong>Email: </strong>{employee.email}</p>
        </div>
      </div>
      <button className='btn btn-secondary mt-3' onClick={goBack}>Back</button>
    </div>
  )
}

export default ViewEmployeeComponent