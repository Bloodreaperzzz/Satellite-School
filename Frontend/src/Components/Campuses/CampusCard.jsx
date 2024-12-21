import { Card } from '@mui/material';
import React from 'react';


const CampusCard = ({ title, image, add }) => {
  return (
    <Card className='w-[18rem] mb-3 mt-3 rounded-md shadow-lg'>
      <section className='w-full h-[10rem]'>
        <img
          className='w-full h-full object-cover rounded-t-md'
          src={image}
          alt={title}
        />
      </section>

      
      <div className="p-4">
        <div className="space-y-2">
          <h2 className="text-xl font-semibold">{title}</h2>
          <p className="text-gray-500">{add}</p>
        </div>
      </div>
    </Card>
  );
};

export default CampusCard;
