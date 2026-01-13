import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './Home.css';

const Home = () => {
  const { user } = useAuth();

  return (
    <div className="home-container">
      <div className="home-content">
        <h1>Game Đoán Số</h1>
        <p className="home-subtitle">Test nhân phẩm của bạn!</p>
        {user ? (
          <div className="home-actions">
            <Link to="/game" className="home-button primary">
              Bắt Đầu Chơi
            </Link>
          </div>
        ) : (
          <div className="home-actions">
            <Link to="/login" className="home-button primary">
              Đăng Nhập
            </Link>
            <Link to="/register" className="home-button secondary">
              Đăng Ký
            </Link>
          </div>
        )}
      
      </div>
    </div>
  );
};

export default Home;
