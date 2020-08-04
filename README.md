# WebSales

FrontEnd
+ Sử dụng "sitemesh decorators"
  
  Khi người dùng truy cập vào 1 url thì nó chia làm 3 loại
  
  - Web: url=/ ->apply giao diện web. tương tự như admin
  
  - Admin: url=/admin -> apply giao diện admin trong file decorators->admin.jsp trong này sẽ là file parent
  phần content của admin.jsp sẽ lấy qua controller
  
  - Api:có url=/api* thì không cần apply giao diện cho các url này
  

BackEnd
+ Sử dụng mô hình 3 tier architecture
  
  - Presentation layer->Hiện thực hóa băng MVC

  - Business Logic layer->Service
  
  - Database layer->DAO
 
+ Sử dụng "commons-beanutils" để mapping các field từ form của tầng view sang model
  
   ví dụ khi login ->có 2 trường là username, password thì nó sẽ mapp sang model là customerModel

+ Sử dụng "servlet filter" để phân quyền. 
  
  - Authentication: đăng nhập với username, password kiểm tra xem trong database có tồn tại hay không
  
  - Authorization: khi người dùng request vào 1 url có phần đầu là /admin thì nó sẽ kiểm tra xem người đó có quyền vào url đó hay không.có 2 TH nếu chưa đăng nhập->yêu cầu đăng nhập. Nếu đã đăng nhập thì kiểm tra xem nó có quyền vào không? nếu không thì chuyển đến trang đăng nhập và thông báo
  
  - Khi đăng nhập ta sử dụng thêm: ResourceBundle trong java để lưu các message thông báo
  
    file này có đường dẫn: src->main->resources: đặt tên file "tên file".properties
    
    file này sẽ lưu các cấu hình của hệ thống: ví dụ connect tới sql, message login, email,...
     
    cú pháp : ResourceBundle resourceBundle=ResourceBundle.getBundle("tên file");resourceBundle.getString("key");//lưu theo kiểu key-value
  
+ Sử dụng "weld" để hỗ trợ tầng trên gọi dịch vụ tầng dưới. Ví dụ bình thường ko sử dụng weld thì khi có 100000 request thì ở controller sẽ tạo 100000 đối tượng ->rất tốn tài nguyên hệ thống. Thay vì đó ta sử dụng weld để khi request tới controller nếu đối tượng đó chưa đc tạo thì nó sẽ tạo mới và lưu vào vùng container lần sau request tới thì nó sẽ không phải tạo mới
  - sử dụng "weld" thông qua từ khóa
  
      @Inject
      
	    private "Tên-interface" "tên-biến";


+ Cookie Là Gì
  - Theo định nghĩa thì cookie là một mẩu thông tin nhỏ được lưu trừ trên trình duyệt người dùng. Đây là một file nhỏ được trình duyệt lưu trữ trên máy tính của bạn. Trên cùng một trình duyệt mỗi một domain khác nhau sẽ có một cookie khác nhau. Ở đây một domain giống như một khách sạn ở ví dụ trên.

  - Bạn cũng cần lưu ý rằng khi bạn truy cập một domain trên nhiều tab khác nhau của cùng một trình duyệt thì trình duyệt này cũng chỉ sử dụng một file cookie duy nhất cho domain trên.

+ Session Là Gì
  - Session là một tập tin nhỏ được lưu trữ trên server, mỗi một file session sẽ tương ứng với một file cookie.

  - Mỗi một cookie sẽ thường tương ứng với một người dùng và bên trong từng file session này sẽ chứa các thông tin liên quan tới người dùng đó. Vừa rồi mình sử dụng từ thông thường là bởi vì một người dùng có thể sử dụng hai trình duyệt khác nhau như Firefox hay Chrome trên một máy để truy cập vào cùng một địa chỉ trang web. Lúc này sẽ có hai cookie trên hai trình duyệt (và do đó sẽ có 2 session được tạo ra trên server) cho cùng một người dùng.

