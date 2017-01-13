package com.icc.baidu_data_webapi.util;

import java.util.List;

public class JsonCoord  
    {  
        public int status;  
  
        public List<Coord> result; 

        public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public List<Coord> getResult() {
			return result;
		}

		public void setResult(List<Coord> result) {
			this.result = result;
		}

    }  
  
    