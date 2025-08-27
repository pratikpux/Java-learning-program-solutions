import React from 'react';

// Sample data
const books = [
  { id: 101, bname: 'Master React', price: 670 },
  { id: 102, bname: 'Deep Dive into Angular 11', price: 800 },
  { id: 103, bname: 'Mongo Essentials', price: 450 }
];

const courses = [
  { id: 1, name: 'Angular', date: '4/5/2021' },
  { id: 2, name: 'React', date: '6/3/20201' }
];

const blogs = [
  { id: 1, title: 'React Learning', author: 'Stephen Biz', content: 'Welcome to learning React!' },
  { id: 2, title: 'Installation', author: 'Schwarzdenier', content: 'You can install React from npm.' }
];

// Book Details Component
const BookDetails = () => {
  // Method 1: Using && operator
  const hasBooks = books.length > 0;
  
  return (
    <div style={{padding: '20px', border: '1px solid #ddd', margin: '10px'}}>
      <h1>Book Details</h1>
      
      {/* Method 1: && operator */}
      {hasBooks && (
        <div>
          {books.map(book => (
            <div key={book.id} style={{marginBottom: '10px', padding: '10px', backgroundColor: '#f9f9f9'}}>
              <h3>{book.bname}</h3>
              <h4>{book.price}</h4>
            </div>
          ))}
        </div>
      )}
      
      {/* Method 2: Ternary operator */}
      <div>
        {books.length > 0 ? `Total Books: ${books.length}` : 'No books available'}
      </div>
    </div>
  );
};

// Blog Details Component
const BlogDetails = () => {
  // Method 3: Using if-else with early return
  if (blogs.length === 0) {
    return (
      <div style={{padding: '20px', border: '1px solid #ddd', margin: '10px'}}>
        <h1>Blog Details</h1>
        <p>No blogs available</p>
      </div>
    );
  }
  
  // Method 4: Function-based conditional rendering
  const renderBlogContent = (blog) => {
    return blog.content.length > 25 ? 
      blog.content.substring(0, 25) + '...' : 
      blog.content;
  };
  
  return (
    <div style={{padding: '20px', border: '1px solid #ddd', margin: '10px'}}>
      <h1>Blog Details</h1>
      
      {blogs.map(blog => (
        <div key={blog.id} style={{marginBottom: '15px', padding: '10px', backgroundColor: '#f0f8ff'}}>
          <h2>{blog.title}</h2>
          <p style={{fontSize: '14px', color: '#666'}}>{blog.author}</p>
          <p>{renderBlogContent(blog)}</p>
        </div>
      ))}
      
      {/* Method 5: Conditional styling */}
      <div style={{
        padding: '10px', 
        backgroundColor: blogs.length > 1 ? '#e6f3ff' : '#f0f0f0',
        marginTop: '10px'
      }}>
        Status: {blogs.length > 1 ? 'Multiple blogs available' : 'Limited content'}
      </div>
    </div>
  );
};

// Course Details Component
const CourseDetails = () => {
  const [selectedCourse, setSelectedCourse] = React.useState(null);
  const [showDetails, setShowDetails] = React.useState(true);
  
  // Method 6: Using state-based conditional rendering
  const toggleDetails = () => {
    setShowDetails(!showDetails);
  };
  
  // Method 7: Switch statement for conditional rendering
  const renderCourseStatus = (courseName) => {
    switch(courseName.toLowerCase()) {
      case 'react':
        return <span style={{color: 'green', fontWeight: 'bold'}}>Popular</span>;
      case 'angular':
        return <span style={{color: 'orange', fontWeight: 'bold'}}>Trending</span>;
      default:
        return <span style={{color: 'gray'}}>Available</span>;
    }
  };
  
  return (
    <div style={{padding: '20px', border: '1px solid #ddd', margin: '10px'}}>
      <div style={{display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '15px'}}>
        <h1>Course Details</h1>
        <button 
          onClick={toggleDetails}
          style={{
            padding: '8px 15px', 
            backgroundColor: '#007bff', 
            color: 'white', 
            border: 'none', 
            borderRadius: '4px',
            cursor: 'pointer'
          }}
        >
          {showDetails ? 'Hide' : 'Show'} Details
        </button>
      </div>
      
      {/* Method 8: Complex conditional with multiple conditions */}
      {showDetails && courses.length > 0 ? (
        <div>
          {courses.map(course => (
            <div 
              key={course.id} 
              style={{
                marginBottom: '10px', 
                padding: '15px', 
                border: selectedCourse === course.id ? '2px solid #007bff' : '1px solid #ddd',
                backgroundColor: selectedCourse === course.id ? '#f0f8ff' : '#fff',
                cursor: 'pointer'
              }}
              onClick={() => setSelectedCourse(course.id)}
            >
              <div style={{display: 'flex', justifyContent: 'space-between'}}>
                <div>
                  <h3>{course.name}</h3>
                  <p style={{color: '#666', fontSize: '14px'}}>{course.date}</p>
                </div>
                <div>
                  {renderCourseStatus(course.name)}
                </div>
              </div>
              
              {/* Method 9: Nested conditional rendering */}
              {selectedCourse === course.id && (
                <div style={{marginTop: '10px', padding: '10px', backgroundColor: '#fff', border: '1px solid #eee'}}>
                  <p style={{fontSize: '14px'}}>
                    {course.name === 'React' ? 
                      'Learn modern React with hooks and context API' : 
                      'Master Angular framework with TypeScript'
                    }
                  </p>
                </div>
              )}
            </div>
          ))}
        </div>
      ) : !showDetails ? (
        <p style={{fontStyle: 'italic', color: '#888'}}>Details hidden</p>
      ) : (
        <p style={{color: '#888'}}>No courses available</p>
      )}
      
      {/* Method 10: Logical operators for conditional rendering */}
      {selectedCourse && showDetails && (
        <div style={{marginTop: '15px', padding: '10px', backgroundColor: '#e6f3e6'}}>
          <p style={{fontWeight: 'bold'}}>
            Selected: {courses.find(c => c.id === selectedCourse)?.name}
          </p>
        </div>
      )}
    </div>
  );
};

// Main App Component
const App = () => {
  const [activeView, setActiveView] = React.useState('all');
  
  // Method 11: Component-level conditional rendering with switch
  const renderView = () => {
    switch(activeView) {
      case 'books':
        return <BookDetails />;
      case 'blogs':
        return <BlogDetails />;
      case 'courses':
        return <CourseDetails />;
      default:
        return (
          <div>
            <BookDetails />
            <BlogDetails />
            <CourseDetails />
          </div>
        );
    }
  };
  
  return (
    <div style={{padding: '20px', fontFamily: 'Arial, sans-serif'}}>
      <header style={{textAlign: 'center', marginBottom: '30px'}}>
        <h1 style={{color: '#333', fontSize: '36px'}}>BloggerApp</h1>
        <p style={{color: '#666'}}>React App with Multiple Conditional Rendering Techniques</p>
        
        {/* Method 12: Navigation with conditional active states */}
        <nav style={{marginTop: '20px'}}>
          {['all', 'books', 'blogs', 'courses'].map(view => (
            <button
              key={view}
              onClick={() => setActiveView(view)}
              style={{
                padding: '10px 20px',
                margin: '0 5px',
                border: 'none',
                borderRadius: '4px',
                cursor: 'pointer',
                textTransform: 'capitalize',
                backgroundColor: activeView === view ? '#007bff' : '#f8f9fa',
                color: activeView === view ? 'white' : '#333'
              }}
            >
              {view === 'all' ? 'All Components' : view}
            </button>
          ))}
        </nav>
      </header>
      
      <main>
        {renderView()}
      </main>
      
      {/* Method 13: Footer with conditional content */}
      <footer style={{textAlign: 'center', marginTop: '30px', padding: '20px', borderTop: '1px solid #ddd'}}>
        <p style={{color: '#888', fontSize: '14px'}}>
          {activeView === 'all' ? 
            'Showing all components with various conditional rendering techniques' :
            `Currently viewing: ${activeView}`
          }
        </p>
      </footer>
    </div>
  );
};

export default App;