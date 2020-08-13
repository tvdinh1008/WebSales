# WebSales

FrontEnd
+ Sử dụng "sitemesh decorators"
  
  Khi người dùng truy cập vào 1 url thì nó chia làm 3 loại
  
  - Web: url=/ ->apply giao diện web. tương tự như admin
  
  - Admin: url=/admin -> apply giao diện admin trong file decorators->admin.jsp trong này sẽ là file parent
  phần content của admin.jsp sẽ lấy qua controller
  
  - Api:có url=/api* thì không cần apply giao diện cho các url này

+ Chú ý với thẻ scripts thì muốn sử dụng con thì cha của nó phải ở trên và link sẽ để ở thẻ <c:> để nó tự động điền:http://localhost:8080/"thư viện"

 	<link href="<c:url value='/template/web/css/style.css' />" rel="stylesheet" type="text/css" media="all"/>

+ Phân trang: /admin-product?page=2&maxPageItem=20&sort=name,desc  : tứ là phân trang theo: mình đang đứng ở trang nào, số sản phẩm hiện thị trên 1 trang, sắp xếp theo chiều nào,trường nào

	- Luồng chạy: khi request vào /admin-product?page=2&maxPageItem=20&sort=name,desc thì controller sẽ lấy dữ liệu xử lý và trả về cho view. Khi click vào 1 trang khác
	thì form sẽ phải có page, maxPageItem, sortName, sortBy

	form ...
	
	        input type="hidden" value="" id="page" name="page"
		
		input type="hidden" value="" id="maxPageItem" name="maxPageItem"
		
		input type="hidden" value="" id="sortName" name="sortName"
		
		input type="hidden" value="" id="sortBy" name="sortBy"
		
		input type="hidden" value="list" id="type" name="type" //cái này thêm vào khi phân trang để nó phân biệt với edit trong TH sử dụng 1 controller
	
	Và trước khi submit cần put dữ liệu cho các thẻ input này ví dụ : $('#page').val(page);... sau đó $('#formSubmit').submit();
	

BackEnd
+ Sử dụng mô hình 3 tier architecture
  
  - Presentation layer->Hiện thực hóa băng MVC// Controller chỉ xử lý :nhận dữ liệu từ client gửi về và đẩy dữ liệu ra ngoài cho view sử dụng

  - Business Logic layer->Service : xử lý phần logic
  
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

+ Khi request vào url='/admin-product' tức vào controller: ProductController của package .admin thì nó sẽ chia ra làm một số kiểu(type)
	- Hiển thị danh sách sản phẩm: type=LIST
	- Thêm, sửa sản phẩm( dựa vào id khi request nếu id=null->là thêm sản phẩm. Nếu id!=null ->query dữ liệu rồi binding vào trường trong view): type=EDIT
	
	value="${model.title}" thì nếu là thêm thì rõ ràng model.title=null thì là ô trống còn nếu là sửa thì có dữ liệu
	
	Khi còn nút submit sẽ dựa vào id nếu=null: ô sẽ là thêm sản phẩm, ngược lại là cập nhật sản phẩm
	
	Do thêm với sửa là 1 trang jsp => ta sẽ kiểm tra ở trong jsp nếu model!=null thì binding ngược lại không
	
	
	->Tránh đc tạo nhiều servlet vì thế khi request ta có thêm VD:url='/admin-product?type=list'
	


