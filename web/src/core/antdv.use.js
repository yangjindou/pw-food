import Vue from "vue";
import 'ant-design-vue/dist/antd.less';
import {
  Button, message, Avatar, Dropdown, Icon, ConfigProvider, Form, Row, Col, Select, Input,
  Upload, Menu, Checkbox, Breadcrumb, Table, DatePicker, Empty, Modal, Switch, Radio,
  Result, Layout, Tooltip, Cascader
} from 'ant-design-vue';
Vue.use(Cascader);
Vue.use(Button);
Vue.use(Avatar);
Vue.use(Dropdown);
Vue.use(Icon);
Vue.use(ConfigProvider);
Vue.use(Form);
Vue.use(Row);
Vue.use(Col);
Vue.use(Select);
Vue.use(Input);
Vue.use(Upload);
Vue.use(Menu);
Vue.use(Checkbox);
Vue.use(Breadcrumb);
Vue.use(Table);
Vue.use(DatePicker);
Vue.use(Empty);
Vue.use(Modal);
Vue.use(Switch);
Vue.use(Radio);
Vue.use(Result);
Vue.use(Layout);
Vue.use(Tooltip);
Vue.prototype.$message = message;
Vue.prototype.$confirm = Modal.confirm;
import UAvatar from "@/components/uAvatar";
Vue.component('u-avatar',UAvatar);
import UUpload from "@/components/uUpload";
Vue.component('u-upload',UUpload);
import UBreadcrumb from "@/components/uBreadcrumb";
Vue.component('u-breadcrumb',UBreadcrumb);